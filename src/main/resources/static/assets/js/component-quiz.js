function writeQuestion(id_question, question, coeff) {
    let box_question = document.getElementById("question");
    let question_component = createQuestionComponent(id_question, question, coeff);
    box_question.innerHTML += question_component;
}

function writeReponse(id_question, id_reponse, reponse) {
    let box_reponse = document.getElementById("reponse-"+id_question);
    let reponse_component = createResponseComponent(id_question, id_reponse, reponse);
    box_reponse.innerHTML += reponse_component;
}

function InitializeQuestion(id_question) {
    let box = document.getElementById("question-box");
    let question_component = createQuestionComponent(id_question);
    box.innerHTML += question_component;
}

// create a component for response
function createResponseComponent(id_question, id_response, reponse) {
    let component =
        "<div> " +
        "<input value='"+ reponse +"' type='text' data-id-question='"+id_question+"' data-id-response='"+id_response+"'>"+
        "<select name='type' data-id-question='"+id_question+"' data-id-response='"+id_response+"'>" +
        "<option value='true'>Vrai</option> " +
        "<option value='false'>Faux</option> " +
        "</select>" +
        "</div>";
    return component;
}

// create a select component for type of question
function createTypeComponent(id_question) {
    let component = "" +
        "<select name='type-"+id_question+"' data-id-question='"+id_question+"'>" +
        "<option value='checkbox'>Choix multiple</option>" +
        "<option value='radio'>Choix unique</option>" +
        "<option value='boolean'>Vrai ou faux</option>" +
        "<option value='text'>Champ libre</option>" +
        "</select>";
    return component;
}

// create a component for question
function createQuestionComponent(id_question, question, coeff) {
    let type_component = createTypeComponent(id_question);
    let component = ""+
        "<div data-id-question='"+id_question+"' id='question-"+id_question+"'>" +
        "<input value='"+question+"' type='text' data-id-question='"+id_question+"' class='question' id='question' onblur='updateQuestionValeur(this)'>" + type_component +
        "<input value='"+coeff+"' type='number' data-id-question='"+id_question+"' onblur='updateQuestionCoefficient(this)'>" +
        "<div id='reponse-"+ id_question +"'></div>" +
        "</div>";
    return component;
}
