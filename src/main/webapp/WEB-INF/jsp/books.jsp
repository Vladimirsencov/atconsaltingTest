<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="header.jsp"/>
<body>
<jsp:include page="bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <p id="loggedUser">${userName}</p>
            <h3>Books</h3>
            <a class="btn btn-sm btn-info" onclick="add()">Add book</a>
            <table class="table table-striped display" id="bookTable">
                <thead>
                <tr>
                    <th>ISBN</th>
                    <th>Author</th>
                    <th>Title</th>
                    <th>Reader</th>
                    <th>Delete</th>
                </tr>
                </thead>
            </table>
            <a class="btn btn-sm btn-info" onclick=showMore()>Swow more</a>
        </div>
    </div>
</div>
</div>


<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title">Book edit</h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="bookForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="ISBN" class="control-label col-xs-3">ISBN</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="ISBN" name="ISBN" placeholder="ISBN">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="authorName" class="control-label col-xs-3">Author</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="authorName" name="authorName"
                                   placeholder="Author">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="title" class="control-label col-xs-3">Title</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="title" name="title" placeholder=Title>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="webjars/jquery/2.2.3/jquery.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/bookDataTable.js"></script>
</html>