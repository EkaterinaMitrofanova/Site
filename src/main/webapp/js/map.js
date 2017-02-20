
    ymaps.ready(init);
    var myMap;

    function init() {
        myMap = new ymaps.Map("map", {
            center: [55.78548493, 49.12097336],
            zoom: 15
        });

        getMarks();
    }

    //здесь на карте должны были выводится все сообщения, в которых указано местоположение.
    //к сожалению, не хватило времени в этом разобраться и оформить
    
    function getMarks() {
        $.ajax({
            type: "post",
            url: "/map",
            success: function (result) {
                if (result != "empty") {
                        console.log("JS: not empty");
                        var name = result.name,
                            date = result.datePost,
                            text = result.text,
                            coords = result.coords;
                        console.log("JS: coords " + coords);

                        var myPlacemark = new ymaps.Placemark(coords, {
                            hintContent: 'Москва!',
                            balloonContent: name + "\n" + date + "\n" + text
                        });

                        myMap.geoObjects.add(myPlacemark);

                    } else {
                        console.log("JS: empty");
                    }

                myMap.controls.add('smallZoomControl', {
                    top: 25,
                    left: 5
                });

            },
            error: function () {
                alert('Error!');
            }
        });
    }


