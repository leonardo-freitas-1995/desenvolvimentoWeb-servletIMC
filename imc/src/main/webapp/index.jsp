<%-- 
    Document   : index
    Created on : May 15, 2016, 2:18:13 AM
    Author     : leona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Calcular IMC</title>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/materialize.min.css"  media="screen,projection"/>


    </head>
    <body>
        <div class="row">
        <div class="col m6 offset-m3">
            <div class="container">
                <h3>IMC</h3>
                <hr>
                <form method = "post" action = "ServletCalcularIMC">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="input-field col m8 offset-m2">
                                <input name="peso" id="peso" type="text" class="validate">
                                <label for="peso">Peso</label>
                            </div>
                        </div>
                    </div>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="input-field col m8 offset-m2">
                                <input name="altura" id="altura" type="text" class="validate">
                                <label for="altura">Altura</label>
                            </div>
                        </div>
                    </div>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="input-field col m8 offset-m2">
                                <select name="sexo" id="sexo">
                                  <option value="masculino">Masculino</option>
                                  <option value="feminino">Feminino</option>
                                </select>
                                <label for="sexo">Sexo</label>
                            </div>
                        </div>
                    </div>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col m8 offset-m2">
                                <button class="waves-effect waves-light btn">Calcular</button>
                            </div>
                        </div>
                    </div>
                </form>
                <hr>
                <%
                    String imcString = request.getParameter("imc");
                    String message = request.getParameter("message");
                    String cardColor = request.getParameter("cardColor");
                    String error = request.getParameter("error");
                    if (imcString != null && message != null && cardColor != null && error == null){
                        double imc = Double.parseDouble(imcString);
                        out.print("<div class='card-panel " + cardColor + " white-text'>IMC: " + imc + " <br>" + message + "</div>");
                    }
                    else if (error != null){
                        out.print("<div class='card-panel red white-text'>" + error + "</div>");
                    }
                %>
            </div>
        </div>
    </div>


    <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>
</html>
