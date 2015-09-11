<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>BCSG</title>

    <link href="<c:url value="/resources/bootstrap-3.3.5/css/bootstrap.min.css"/>" rel="stylesheet"/>

    <style>
        .container.upload #buttons,
        .container.addCard #buttons,
        .container #uploadCsvForm,
        .container #addCardForm
        {
            display: none;
        }

        .container.addCard #addCardForm,
        .container.upload #uploadCsvForm
        {
            display: block;
        }


    </style>

</head>
<body>

<div id="mainNode" class="container ${mainNodeCssClass}">

    <div class="block-header">
        <img id="newsroom-header-logo" title="Business Centric Services Group"
             src="<c:url value="/resources/images/bcsg_logo.jpeg" />"/>
    </div>

    <p>
        <c:if test="${not empty message}">
            <div id="message" class="success">${message}</div>
        </c:if>

        <c:if test="${status.error}">
            <div id="message" class="error">Form has errors</div>
        </c:if>
    </p>

    <div class="row">
        <div class="col-sm-8 blog-main">

            <table class="table">
                <tr>
                    <th>Bank</th>
                    <th>Card Number</th>
                    <th>Expiry Date</th>
                </tr>
                <c:forEach var="card" items="${cards}">
                <tr>
                    <td>${card.bank}</td>
                    <td>${card.truncatedNumber}</td>
                    <td><fmt:formatDate pattern="MMM-yyyy" value="${card.expiry}" /></td>
                </tr>
                </c:forEach>

            </table>

            <div id="buttons">

                <button type="button" class="btn btn-default" aria-label="Left Align" onclick="$('#mainNode').addClass('addCard')">
                    <span class="glyphicon glyphicon-user glyphicon-align-left" aria-hidden="true"></span>
                    Enter new Card
                </button>

                <button type="button" class="btn btn-default" aria-label="Left Align" onclick="$('#mainNode').addClass('upload')">
                    <span class="glyphicon glyphicon-open-file glyphicon-align-left" aria-hidden="true"></span>
                    Upload CSV
                </button>

            </div>

        </div>
    </div>

    <!-- upload CSV form -->
    <div id="uploadCsvForm" class="row">
        <div class="col-sm-8 blog-main">

            <c:url var="uploadActionUrl" value="/form/fileUploadCsv"/>
            <form id="fileUploadForm" action="${uploadActionUrl}" method="POST" enctype="multipart/form-data">

                <h2 class="form-signin-heading">
                    <small>Please choose a CSV file</small>
                </h2>

                <div class="form-group">
                    <label for="file" class="sr-only">File</label>
                    <input type="file" id="file" name="file" class="form-control" placeholder="CSV File" required autofocus>
                </div>

                <button type="button" class="btn btn-cancel" onclick="$('#mainNode').removeClass('upload')">Cancel</button>
                <button type="submit" class="btn btn-default">Upload</button>

            </form>

        </div>
    </div>

    <!-- add card form -->
    <div id="addCardForm" class="row">
        <div class="col-sm-8 blog-main">

            <c:url var="addCardActionUrl" value="/form/addCard"/>
            <form:form id="form" method="post" action="${addCardActionUrl}" modelAttribute="addCardFormBean">

                <h2 class="form-signin-heading">
                    <small>Please enter your card details</small>
                </h2>

                <div class="form-group">
                    <form:label path="bank">
                        Bank <form:errors path="bank" cssClass="error" />
                    </form:label>
                    <form:input path="bank" cssClass="form-control" placeholder="Bank" />
                    <%--<label for="bank">Bank</label>
                    <input type="text" id="bank" class="form-control" placeholder="Bank" required autofocus>--%>
                </div>

                <div class="form-group">
                    <form:label path="bank">
                        Card Number <form:errors path="number" cssClass="error" />
                    </form:label>
                    <form:input path="number" cssClass="form-control" placeholder="Card Number" />
                    <%--<label for="cardNumber">Card Number</label>
                    <input type="text" id="cardNumber" class="form-control" placeholder="Card Number" required autofocus>--%>
                </div>

                <div class="form-group">
                    <form:label path="expiry">
                        Expiry Date (mm/yyyy) <form:errors path="expiry" cssClass="error" />
                    </form:label>
                    <form:input path="expiry" cssClass="form-control" placeholder="Expiry Date" />
                    <%--<label for="expiryDate">Expiry Date (mm/yyyy)</label>
                    <input type="text" id="expiryDate" class="form-control" placeholder="Expiry Date" required autofocus>--%>
                </div>

                <button type="button" class="btn btn-cancel" onclick="$('#mainNode').removeClass('addCard')">Cancel</button>
                <button type="submit" class="btn btn-default">Submit</button>

            </form:form>

        </div>
    </div>

</div>

<script src="<c:url value="/resources/jquery/1.11.3/jquery-1.11.3.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap-3.3.5/js/bootstrap.min.js" />"></script>

<footer><p></p></footer>

</body>
</html>
