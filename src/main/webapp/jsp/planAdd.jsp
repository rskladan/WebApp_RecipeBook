<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Zaplanuj Jedzonko</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
        rel="stylesheet">
  <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head></head>
<body>
<jsp:include page="/jsp/appSideMenu.jsp"/>

    <div class="m-4 p-3 width-medium">
      <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <!-- fix action, method -->
        <!-- add name attribute for all inputs -->
        <form action="/app/plan/add" method="post">
          <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
              <h3 class="color-header text-uppercase">NOWY PLAN</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
              <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
            </div>
          </div>

          <div class="schedules-content">

            <div class="form-group row">
              <label for="planName" class="col-sm-2 label-size col-form-label">
                Nazwa planu
              </label>
              <div class="col-sm-10">
                <input name="planName" class="form-control" id="planName" placeholder="Nazwa planu">
              </div>
            </div>
            <div class="form-group row">
              <label name="planDescription" class="col-sm-2 label-size col-form-label">
                Opis planu
              </label>
              <div class="col-sm-10">
                <textarea class="form-control" rows="5" id="planDescription"
                                          placeholder="Opis plany"></textarea>
              </div>
            </div>

          </div>
        </form>
      </div>
    </div>
  </div>
</section>

<jsp:include page="/jsp/footer.jsp"/>

</body>
</html>
