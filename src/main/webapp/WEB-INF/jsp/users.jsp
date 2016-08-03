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

                <table class="table table-striped display" id="dataTable">
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
                            <label for="name" class="control-label col-xs-3">Name</label>

                            <div class="col-xs-9">
                                <input type="text" class="form-control" id="name" name="name" placeholder="Name">
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
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="resources/js/userDataTable.js"></script>

</html>
