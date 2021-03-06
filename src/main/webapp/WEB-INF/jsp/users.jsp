<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="header.jsp"/>
<body>
<jsp:include page="bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <p id="loggedUser">${userName}</p>
            <h3>Users</h3>
            <div class="view-box">
                <a class="btn btn-sm btn-info" onclick="add()">Add user</a>

                <table class="table table-striped display" id="userTable">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                </table>

            </div>
        </div>
    </div>

    <div class="modal fade" id="editRow">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h2 class="modal-title">User edit</h2>
                </div>
                <div class="modal-body">
                    <form:form class="form-horizontal" method="post" id="userForm">
                        <input type="text" hidden="hidden" id="id" name="id">

                        <div class="form-group">
                            <label for="name" class="control-label col-xs-3">useName</label>

                            <div class="col-xs-9">
                                <input type="text" class="form-control" id="userName" name="userName"
                                       placeholder="userName">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="control-label col-xs-3">Password</label>

                            <div class="col-xs-9">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="password">
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
</div>
</body>
<script type="text/javascript" src="webjars/jquery/2.2.3/jquery.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/userDataTable.js"></script>
</html>
