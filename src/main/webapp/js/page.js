function addDate() {
    var date = moment();
    return date.format('DD MMMM YYYY, HH:mm');
}

var myMap;

function sub(del, lik){
//отменяем стандартное действие при отправке формы
//     e.preventDefault();
// //берем из формы метод передачи данных
//     var m_method=$(this).attr('method');
// //получаем адрес скрипта на сервере, куда нужно отправить форму
//     var m_action=$(this).attr('action');
// //получаем данные, введенные пользователем в формате input1=value1&input2=value2...,
// //то есть в стандартном формате передачи данных формы
//     var m_data=$(this).serialize();
    var area = $('#area'),
        div = $('#mainContent'),
        coordinates = $('#map').attr('data-coords');

    if (!/\S/i.test(area.val()) ){
        // $('span').css('display', 'block');
        return false;
    } else {
        var location = $('#map').attr('data-location');
        if (location == "0"){
            location = "";
        }
        $.ajax({
            type: "post",
            url: "/addPost",
            data: { 'text': area.val(), 'date': addDate(), 'coords': coordinates, "loc": location },
            success: function (result) {
                var id = result.id,
                    date = result.date,
                    coords = result.coords;
                    var html = '<div class="mainContent-post" data-id ='+id+' > <div class="post-middleLine"> <div class="post-header"> <a class="post-photo" href="#"> ' +
                        '<img src="../images/ava6.jpg"> </a> <div class="post-info"> <div class="post-author">' + $('#nickname').text() + '</div> <div class="post-date">' + date + '</div> </div> ' +
                        '<a class="delete" data-id ='+ date +' href="#">' + del + '</a> </div> <div class="post-text">' + area.val() + '</div> ' +
                        '<div class="post-image"> <img > </div> </div> <div class="post-bottomLine"> <a href="#" class="post-likeImg"> ' +
                        '<div class="post-like"></div> </a> <div class="post-amountLikes">'+ location +'</div> </div>  </div>';
                    div.prepend(html);
                deleteMap();
            },
            error: function () {
                alert('Error!');
            }
        });
    }
}

function deletePost(e) {
    var id = $(e).attr('data-id'),
        div = $('.mainContent-post[data-id = '+id+']');
    $.ajax({
        type: "post",
        url: "/deletePost",
        data: {'idPost': id},
        success: function (result) {
            if (result == "200") {
                div.remove();
            }
        },
        error: function () {
            alert('Error!');
        }
    });
}

function toTop() {
    $("html, body").animate({ scrollTop: 0 }, "slow");
    return false;
}

function getLocation(coords) {

    myGeocoder = ymaps.geocode([coords]);
        myGeocoder.then(function (res) {

            var geoObject = res.geoObjects.get(0);
            var text = geoObject.properties.get('text');
            var div = $('#map');
            div.attr('data-location', text );
        });

}

function deleteMap() {
    myMap.destroy();
    var div = $('#map');
    div.css('display', 'none');
}

function getMap() {
    var div = $('#map');
    div.css('display', 'block');
    ymaps.ready(init);

    function init() {
        myMap = new ymaps.Map("map", {
            center: [55.78548493, 49.12097336],
            zoom: 16,
            controls: ['zoomControl','fullscreenControl']
        });
        var searchControl = new ymaps.control.SearchControl({
            options: {

                provider: 'yandex#search'
            }
        });

        myMap.controls.add(searchControl);
        window.mark = new ymaps.Placemark([55.78548493, 49.12097336], {}, { draggable: 1 });
        myMap.geoObjects.add(mark);


        searchControl.events.add('resultselect', function (e) {

            var results = searchControl.getResultsArray(),
                selected = e.get('index'),
                point = results[selected].geometry.getCoordinates();

            myMap.geoObjects.remove(mark);
            window.mark1 = new ymaps.Placemark(point, {}, { draggable: 1 });
            myMap.geoObjects.add(mark1);
            var div = $('#map');
            div.attr('data-coords', point.join(',') );
            getLocation(point.join(','));
        });

        mark.events.add('dragend', function (e) {
            var coordinates = e.get('target').geometry.getCoordinates();
            var div = $('#map');
            div.attr('data-coords', coordinates.join(','));
            getLocation(coordinates.join(','));
        });

    }
}
