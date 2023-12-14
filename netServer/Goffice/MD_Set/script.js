document.addEventListener('DOMContentLoaded', function () {
    var heightSlider = document.getElementById('height-slider');
    var widthSlider = document.getElementById('width-slider');

    heightSlider.addEventListener('input', function () {
        adjustIframeSize('height', heightSlider.value + 'px');
    });

    widthSlider.addEventListener('input', function () {
        adjustIframeSize('width', widthSlider.value + 'px');
    });

    function adjustIframeSize(dimension, value) {
        var iframes = document.querySelectorAll('iframe');
        iframes.forEach(function (iframe) {
            iframe.style[dimension] = value;
        });
    }
});
