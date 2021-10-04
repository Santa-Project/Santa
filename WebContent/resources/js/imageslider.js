//mountaininfo 이미지 슬라이더 
(function($){
    $.fn.imageSlider = function(options) {
        this.each(function(index){
            var imageSlider = new ImageSlider(this, options);
        });
        return this;
    }
})(jQuery);

function ImageSlider(selector, _options) {
    this.$imageSlider = null;   
    this._$images = null;       

    this._currentIndex = -1;
    this._imageWidth = 0;

    this._$indexItems = null;

    this._timerID = 0;


    this._options = null;


    this._init(selector);
    this._initImages();
    this._initOptions(_options);

    this._initEvent();

    this.showImageAt(this._options.startIndex);  

    this.startAutoPlay();  

}

ImageSlider.defaultOptions = {
    startIndex : 0,
    autoPlay : true,
    autoPlayDelayTime : 2000,
    animationDuration : 1000,
    animationEasing : "easeOutQuint"
}
ImageSlider.prototype._initOptions = function(_options) {
    this._options = $.extend({}, ImageSlider.defaultOptions, _options);
}

ImageSlider.prototype._init = function(selector) {
    this.$imageSlider = $(selector);
    this._$images = this.$imageSlider.find(".image-list img");
    
    this._imageWidth = this.$imageSlider.find(".slider-body").width();

    this._$indexItems = this.$imageSlider.find(".index-nav li a");
}

ImageSlider.prototype._initImages = function() {
    this._$images.each(function(){
        $(this).css({
            opacity : 0.0
        });
    });
}

ImageSlider.prototype._initEvent = function() {
    var objThis = this;
    this.$imageSlider.find(".slider-btn-prev").on("click", function(){
        objThis.prevImage();
    });
    this.$imageSlider.find(".slider-btn-next").on("click", function(){
        objThis.nextImage();
    });

    this._$indexItems.on("mouseenter", function(){
        var index = objThis._$indexItems.index(this);
        if(objThis._currentIndex > index) {
            objThis.showImageAt(index, "prev");
        }
        else{
            objThis.showImageAt(index, "next");
        }
    });

    this.$imageSlider.on("mouseenter", function(){
        objThis.stopAutoPlay();
    });
    this.$imageSlider.on("mouseleave", function(){
        objThis.startAutoPlay();
    });
}
ImageSlider.prototype.prevImage = function() {

    this.showImageAt(this._currentIndex - 1, "prev");
}
ImageSlider.prototype.nextImage = function() {

    this.showImageAt(this._currentIndex + 1, "next");
}

ImageSlider.prototype.showImageAt = function(index, direction) {
    if(index < 0) {  
        index = this._$images.length - 1; 
    }

    if(index >= this._$images.length) { 
        index = 0;
    }

    var $currentImage = this._$images.eq(this._currentIndex);
    var $newImage = this._$images.eq(index);


    if(direction == "prev" || direction == "next") {
        var currentEndLeft = this._imageWidth;
        var nextStartLeft = -this._imageWidth;

        if(direction == "next") {
            currentEndLeft = -this._imageWidth;  
            nextStartLeft = this._imageWidth;    
        }
        $currentImage.stop().animate({
            left : currentEndLeft,
            opacity : 0.0
        }, this._options.animationDuration, this._options.animationEasing);
        $newImage.css({
            left : nextStartLeft,
            opacity : 0.0 
        });
        $newImage.stop().animate({
            left : 0,
            opacity : 1.0
        }, this._options.animationDuration, this._options.animationEasing);
    }
    else {  
        $currentImage.css({
            opacity : 0
        });

        $newImage.css({
            left : 0,
            opacity : 1.0
        });
    }
    this._selectIndexAt(index);


    var oldIndex = this._currentIndex;
    this._currentIndex = index;
    this._dispatchChangeEvent(oldIndex, this._currentIndex);
}
ImageSlider.prototype._selectIndexAt = function(index){
    if(this._currentIndex != -1) {
        this._$indexItems.eq(this._currentIndex).removeClass("select");    
    }
    this._$indexItems.eq(index).addClass("select");    
}

ImageSlider.prototype._dispatchChangeEvent = function(oldIndex, newIndex) {
    var event = jQuery.Event("change");
    event.oldIndex = oldIndex;
    event.newIndex = newIndex;
    
    this.$imageSlider.trigger(event);
    console.log("change이벤트 발생함.");
}

ImageSlider.prototype.startAutoPlay = function(){
    if(this._options.autoPlay == true) {
        if(this._timerID == 0){
            this._timerID = setInterval($.proxy(function(){
                this.nextImage();
            }, this), this._options.autoPlayDelayTime);
        }
    }
}
ImageSlider.prototype.stopAutoPlay = function() {
    if(this._options.autoPlay == true) {
        if(this._timerID != 0) {
            clearInterval(this._timerID);
            this._timerID = 0;            
        }
    }
}