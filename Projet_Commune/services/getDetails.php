<?php
set_include_path('..'.PATH_SEPARATOR);
require_once('lib/common_service.php');
require_once('lib/fonctions_parms.php');
$dsn = require('lib/dsn_perso_def.php');

try {
    $insee = checkString('insee', NULL, TRUE);
    if ($insee === NULL) {
        throw new ParmsException("Le paramètre insee est obligatoire et ne peut pas être vide");
    }

    $data = new DataLayer($dsn);
    $details = $data->getDetails($insee);

    if ($details === NULL) {
        throw new ParmsException("Le numéro INSEE ne correspond à aucune commune de la MEL");
    }

    echo produceResultAnswer($details);
} catch (ParmsException $e) {
    echo produceErrorAnswer($e->getMessage());
} catch (PDOException $e) {
    echo produceErrorAnswer($e->getMessage());
}
?>
