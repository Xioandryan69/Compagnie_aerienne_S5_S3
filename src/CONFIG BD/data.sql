 
-- =========================
-- AEROPORTS
-- =========================
INSERT INTO aeroport (code_iata, nom, ville, pays) VALUES
('TNR', 'Ivato', 'Antananarivo', 'Madagascar'),
('CDG', 'Charles de Gaulle', 'Paris', 'France'),
('JFK', 'John F. Kennedy', 'New York', 'USA'),
('LHR', 'Heathrow', 'London', 'UK');

-- =========================
-- AVIONS
-- =========================
INSERT INTO avion (modele, capacite, etat) VALUES
('Airbus A320', 180, 'DISPONIBLE'),
('Boeing 737', 160, 'DISPONIBLE'),
('Embraer E195', 120, 'MAINTENANCE');

-- =========================
-- VOLS
-- =========================
INSERT INTO vol (numero_vol, date_vol, heure_depart, heure_arrivee, prix, depart_iata, arrivee_iata, avion_id) VALUES
('MK101', '2026-01-15', '08:00', '16:00', 450.00, 'TNR', 'CDG', 1),
('MK102', '2026-01-16', '10:00', '18:00', 470.00, 'TNR', 'CDG', 2),
('MK201', '2026-01-15', '12:00', '16:00', 300.00, 'CDG', 'JFK', 1),
('MK301', '2026-01-17', '14:00', '22:00', 500.00, 'TNR', 'JFK', 2);

-- =========================
-- PASSAGERS
-- =========================
INSERT INTO passager (nom, prenom, date_naissance, numero_document, nationalite, email, telephone) VALUES
('Andrianirina', 'Liana', '1998-06-10', 'P1234567', 'Madagascar', 'liana@gmail.com', '+261330000001'),
('Rakoto', 'Hery', '1995-09-20', 'P2345678', 'Madagascar', 'hery@gmail.com', '+261330000002'),
('Rabe', 'Naina', '2000-03-15', 'P3456789', 'Madagascar', 'naina@gmail.com', '+261330000003');

-- =========================
-- RESERVATIONS
-- =========================
INSERT INTO reservation (date_reservation, statut, vol_id, passager_id) VALUES
('2026-01-01 09:00:00', 'EN_COURS', 1, 1),
('2026-01-02 10:30:00', 'PAYEE', 2, 2),
('2026-01-03 14:45:00', 'EN_COURS', 3, 3);

-- =========================
-- OPTIONS SUPPLEMENTAIRES
-- =========================
INSERT INTO option_supplementaire (type_option, description, prix, reservation_id) VALUES
('BAGAGE', '+1 bagage 23kg', 30.00, 1),
('REPAS', 'Repas végétarien', 10.00, 1),
('ASSURANCE', 'Assurance voyage', 20.00, 2),
('PRIORITE', 'Embarquement prioritaire', 15.00, 3);