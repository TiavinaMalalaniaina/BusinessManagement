
INSERT INTO status_position (id, "type") VALUES
    (default , 'Ouvert'),
    (default , 'Fermer'),
    (default , 'Recrutement');

INSERT INTO department (id, name, description, creation_date, mission) VALUES
    (default , 'Département des ventes', 'Responsable de la vente de nos produits', '2023-01-15', 'Atteindre les objectifs de vente'),
    (default , 'Département informatique', 'Conduite de recherches innovantes', '2023-02-01', 'Développer de nouvelles technologies'),
    (default , 'Département des ressources humaines', 'Gestion du personnel et du recrutement', '2023-03-10', 'Recruter et gérer des talents');

INSERT INTO person (id, first_name, last_name, date_of_birth, gender, address, phone_number, cin_number, profile) VALUES
    (default, 'John', 'Doe', '1990-05-15', 'Homme', '456 Rue des Roses, Ville', '123-456-7890', '123.456.789.012', 'Employé'),
    (default , 'Jane', 'Smith', '1988-09-25', 'Femme', '789 Avenue des Lys, Ville', '987-654-3210', '987.654.321.098', 'Manager'),
    (default , 'Bob', 'Johnson', '1995-03-08', 'Homme', '123 Boulevard des Étoiles, Ville', '555-555-5555', '546.578.555.431', 'Ingénieur'),
    (default , 'Alice', 'Martin', '1992-08-20', 'Femme', '321 Avenue des Roses, Ville', '555-123-7890', '987.654.321.012', 'Ingénieur'),
    (default , 'David', 'Johnson', '1994-03-15', 'Homme', '456 Rue de la Révolution, Ville', '999-888-7777', '567.890.123.456', 'Développeur');

INSERT INTO position (id, title, description, benefits, creation_date, remarks, upper_hierarchy, departement_id, status_position_id) VALUES
    (default , 'Développeur Web', 'Conception et développement de sites web', 'Avantages compétitifs', '2023-10-15', 'Poste ouvert à l évolution', null, 'DEP0002', 'ST0001'),
    (default , 'Chef de Projet', 'Gestion de projets informatiques', 'Avantages sociaux', '2023-11-05', 'Responsable de l équipe de projet', null, 'DEP0002', 'ST0001'),
    (default , 'Ressources Humaines', 'Gestion des RH et du recrutement', 'Programme de formation', '2023-12-01', 'Rôle clé dans la gestion des employés', null, 'DEP0003', 'ST0001');


select * from contract c
    join person pe on pe.id = c.person_id
    join position po on po.id = c.position_id
