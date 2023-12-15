function filterUsers(){
    //recuperation de la valeur du champs du filtre
    let filterValue = document.getElementById("filterUsername").value;
    console.log(filterValue);

    //requete AJAX vers Spring pour le filtrage cote serveur
    $.get('/user/liste/filterusers?username=' + filterValue, function(data) {
        // Remplacer le contenu de la table avec les résultats filtrés
        $('tbody').html($(data).find('tbody').html());
    });
}