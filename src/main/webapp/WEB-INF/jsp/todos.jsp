        <%@ include file="common/header.jspf" %>
        <%@ include file="common/navigation.jspf" %>
	    <div class="container">
            <p>Welcome ${name}. Start doing stuff</p>
            <h2>${name} TO DO List</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>Target Date</th>
                        <th>Is Done?</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${todos}" var="todo">
                        <tr>
                            <td>${todo.description}</td>
                            <td>${todo.targetDate}</td>
                            <td>${todo.done}</td>
                            <td><a href="edit-todo?id=${todo.id}" class="btn btn-success">EDIT</a></td>
                            <td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">DELETE</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div>
                <a href="add-todo" class="btn btn-success">Add Todo</a>
            </div>
        </div>
        <%@ include file="common/footer.jspf" %>