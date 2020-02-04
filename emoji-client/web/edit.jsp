<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edycja emotikony</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"
            integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm"
            crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/cosmo/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-qdQEsAI45WFCO5QwXBelBe1rR9Nwiss4rGEqiszC+9olH1ScrLrMQr1KmDR964uZ" crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <script>
        $(window).on("load", function () {
            $("#header").load("header.html");
        });
    </script>
</head>
<body>
<div id="header"></div>

<div class="container mt-5">
    <div class="text-center">
        <img src="data:image/jpg;base64,${emoji.base64Image}" width="128" height="128" class="rounded">
    </div>
    <form action="/update" method="post" enctype="multipart/form-data">
        <input style="display: none" type="text" value="${emoji.id}" name="id">
        <label for="nameInput">Nazwa</label>
        <input type="text" id="nameInput" class="form-control mb-4" name="name" maxlength="128" value="${emoji.name}">
        <label for="descriptionTextArea">Opis</label>
        <textarea class="form-control rounded-0 mb-4" id="descriptionTextArea" rows="4" maxlength="1024"
                  name="description">${emoji.description}</textarea>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"
                   checked onclick="check()">
            <label class="form-check-label" for="inlineRadio1">Nie zmieniaj ikonki</label>
        </div>
        <div class="form-check form-check-inline mb-4">
            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"
                   onclick="check()">
            <label class="form-check-label" for="inlineRadio2">Zmień ikonkę</label>
        </div>
        <div class="input-group mb-4">
            <div class="input-group-prepend">
                <span class="input-group-text" id="inputGroupFileAddon01">Ikona</span>
            </div>
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="inputGroupFile01"
                       aria-describedby="inputGroupFileAddon01" name="icon" accept="/image/*" disabled>
                <label class="custom-file-label" for="inputGroupFile01">Wybierz ikonkę</label>
            </div>
        </div>
        <button type="submit" id="submit" class="btn btn-primary">Zatwierdź</button>
    </form>
</div>
<script>
    function check() {
        if (document.getElementById("inlineRadio2").checked) {
            document.getElementById('inputGroupFile01').disabled = false;
        } else if (document.getElementById("inlineRadio1").checked) {
            document.getElementById('inputGroupFile01').disabled = true;
        }
    }

    $('#inputGroupFile01').on('change', function () {
        const fileName = $(this).val();
        $(this).next('.custom-file-label').html(fileName);
    })
</script>
</body>
</html>
