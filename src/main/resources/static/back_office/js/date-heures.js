function formaterDate(date) {
  const jour = String(date.getDate()).padStart(2, '0');
  const mois = String(date.getMonth() + 1).padStart(2, '0'); 
  const annee = date.getFullYear();

  return jour + '-' + mois + '-' + annee;
}
function afficherHeureEnTempsReel() {
   const dateActuelle = new Date();
   //dateActuelle.setHours(dateActuelle.getHours() + 3);
   const dateComplete = formaterDate(dateActuelle);
   const heureComplete = dateActuelle.toLocaleTimeString();
   document.getElementById("date-heures").textContent = dateComplete+" "+heureComplete;
 }
 
 // Mettre à jour l'heure chaque seconde
 setInterval(afficherHeureEnTempsReel, 1000);
 