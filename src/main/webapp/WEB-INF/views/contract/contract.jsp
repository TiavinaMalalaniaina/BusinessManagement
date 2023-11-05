<%@ page import="java.util.Vector" %>
<%@ page import="com.example.rh.Model.person.Person" %>
<%@ page import="com.example.rh.Model.position.Position" %>
<%
    Vector<Person> personVector = (Vector<Person>) request.getAttribute("person");
    Vector<Position> positionVector = (Vector<Position>) request.getAttribute("position");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contract</title>
</head>
<body>
<form action="insert-contract" method="get">
    <p> Choix de personne:
        <% for(Person person : personVector) { %>
        <select name="person">
            <option value="<%= person.getId() %>"><%= person.getFirstName() %></option>
        </select>
        <% } %>
    </p>

    <p> Choix de poste:
        <% for (Position position : positionVector) { %>
        <select name="position">
            <option value="<%= position.getId() %>"><%= position.getTitle() %></option>
        </select>
    </p>

    <p> Debut de contrat:
        <input type="date" name="start">
    </p>

    <p> Fin de contrat:
        <input type="date" name="end">
    </p>

    <p> Est-il en periode de teste:
        <%--@declare id="vrai"--%><%--@declare id="faux"--%><input type="radio" id="true" name="choix" value="true">
        <label for="vrai">Vrai</label>

        <input type="radio" id="false" name="choix" value="false">
        <label for="faux">Faux</label>
    </p>

    <p> Salaire de base en Ariary:
        <input type="number" name="salary">
    </p>

    <p> Type de contrat:
        <input type="text" name="type">
    </p>

    <input type="submit" value="Valider">
</form>

</body>
</html>