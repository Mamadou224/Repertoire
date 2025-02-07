
window.addEventListener('load', initPage);

/**
 *  init de la page 
 */
function initPage() {
    // chargement de la liste d'options (territoires proposés)
    majOptionsTerritoires();
    // gestion de la validation du formulaire
    document.forms.form_communes.addEventListener("submit", sendForm); 

    // centrage de la carte géographique sur le territoire courant
    document.forms.form_communes.territoire.addEventListener("change", function () {
        centerMapElt(this[this.selectedIndex]);
    });
}

/**
 * extrait le  résultat du service de l'objet "meta" Answer
 * @param {*} answer  (objet "meta" Answer reçu du service)
 * @returns résultat du service
 * @throws Error en cas de problème
 */
function processAnswer(answer) {
    if (answer.status == "ok")
        return answer.result;
    else
        throw new Error(answer.message);
}

/**
 * déclenche la mise à jour des options
 */
function majOptionsTerritoires() {
    fetchFromJson('services/getTerritoires.php')
        .then(processAnswer)
        .then(makeOptions);
}

/**
 * Création de la liste des options du <select>
 * @param {*} tab  : liste de territoires
 */
function makeOptions(tab) {
    const select_elt = document.forms.form_communes.territoire; // chanmp de saisie de name territoires dans le formulaire form_communes 
    select_elt.replaceChildren(new Option("Tous", "")); // vide et indère une première option <option value="">Tous</option>
    for (let territoire of tab) {
        const option = new Option(territoire.nom, territoire.id);
        select_elt.appendChild(option);
        // ajouter les geo coord à l'élément option, dans des attributs data-
        for (let k of ['min_lat', 'min_lon', 'max_lat', 'max_lon']) {
            option.dataset[k] = territoire[k];
        }
    }
}

/**
 * Gestionnaire évènement "submit" sur le formulaire
 * déclenche la mise à jour de la liste des communes
 * @param {*} ev : évènement géré
 */
function sendForm(ev) {
    ev.preventDefault();
    const form = ev.target;
    const formData = new FormData(form);
    const territoire = formData.get('territoire');
 
    fetch(`/~mamadou-baillo.sow.etu/TD6/services/getCommunes.php?territoire=${territoire}`)
        .then(response => response.json())
        .then(data => makeCommunesItems(data.result))
        .catch(error => console.error('Error:', error));
}

 

/**
 * affiche une liste de communes dans la liste liste_communes
 * @param {*} tab : liste des communes à afficher
 */
/**
 * Affiche une liste de communes dans la liste liste_communes
 * @param {*} tab : liste des communes à afficher
 */
function makeCommunesItems(tab) {
    const listeCommunes = document.getElementById('liste_communes');
    listeCommunes.innerHTML = '';

    tab.forEach(commune => {
        const li = document.createElement('li');
        li.textContent = commune.nom;
        li.dataset.insee = commune.insee;
        li.dataset.lat = commune.lat;
        li.dataset.lon = commune.lon;
        li.dataset.minLat = commune.min_lat;
        li.dataset.minLon = commune.min_lon;
        li.dataset.maxLat = commune.max_lat;
        li.dataset.maxLon = commune.max_lon;

        li.addEventListener('mouseover', () => centerMapElt(li));

        li.addEventListener('click', function() {
            fetchFromJson(`https://webtp.fil.univ-lille.fr/~mamadou-baillo.sow.etu/TD6/services/getDetails.php?insee=${this.dataset.insee}`)
                .then(data => {
                    if (data && data.status === "ok" && data.result) {
                        displayCommune(data.result);
                    } else {
                        console.error("Données invalides reçues du serveur", data);
                    }
                })
                .catch(error => console.error('Erreur:', error));
        });

        listeCommunes.appendChild(li);
    });
}

/**
 * Affiche les détails d'une commune dans l'élément <div id="details">
 *
 * @param {Object} commune - Un objet représentant une commune.
 * @param {string} commune.nom - Le nom de la commune.
 * @param {string} commune.insee - Le code INSEE de la commune.
 * @param {string} commune.nom_terr - Le nom du territoire auquel appartient la commune.
 * @param {string} commune.lat - La latitude de la commune.
 * @param {string} commune.lon - La longitude de la commune.
 * @param {number} commune.surface - La surface de la commune en mètres carrés.
 * @param {number} commune.perimetre - Le périmètre de la commune en mètres.
 * @param {number} commune.pop2016 - La population de la commune en 2016.
 *
 * Cette fonction met à jour le contenu de l'élément <div id="details">
 * pour afficher les informations détaillées d'une commune sous forme d'un tableau HTML.
 * Elle appelle également la fonction createDetailMap(commune) pour afficher
 * la carte des limites de la commune.
 */

function displayCommune(commune) {
    const detailsDiv = document.getElementById('details');
    detailsDiv.innerHTML = `
        <h2>Détails de la commune : ${commune.nom}</h2>
        <table>
            <tr><th>Code INSEE</th><td>${commune.insee}</td></tr>
            <tr><th>Nom du Territoire</th><td>${commune.nom_terr}</td></tr>
            <tr><th>Latitude</th><td>${commune.lat}</td></tr>
            <tr><th>Longitude</th><td>${commune.lon}</td></tr>
            <tr><th>Surface</th><td>${(commune.surface / 1000000).toFixed(2)} km²</td></tr>
            <tr><th>Périmètre</th><td>${(commune.perimetre / 1000).toFixed(2)} km</td></tr>
            <tr><th>Population (2016)</th><td>${commune.pop2016} habitants</td></tr>
        </table>
    `;
    
    // Appel de la fonction pour créer la carte des limites de la commune
    createDetailMap(commune);
}


/**
 * Centre la carte sur les limites géographiques portées par l'élément
 * @param {*} elt un élément DOM dont le dataset doit posséder les 4 attributs : min_lat, min_lon, max_lat, max_lon
 */
function centerMapElt(elt) {
    let ds = elt.dataset;
    if (ds.minLat && ds.minLon && ds.maxLat && ds.maxLon) {
        map.fitBounds([
            [parseFloat(ds.minLat), parseFloat(ds.minLon)],
            [parseFloat(ds.maxLat), parseFloat(ds.maxLon)]
        ]);
    }
}

