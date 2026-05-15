INSERT INTO habitats(name)
VALUES
('Grassland'),
('Coastal'),
('Underdark'),
('Arctic'),
('Desert');

INSERT INTO creatures(name, habitat_id, status)
VALUES
('Aurochs', 1, 'ACTIVE'),
('Giant Swan', 2, 'ACTIVE'),
('Stirge', 3, 'ACTIVE'),
('Blood Hawk', 4, 'ACTIVE'),
('Spider King', 5, 'ACTIVE');

INSERT INTO users(full_name, email, phone, role)
VALUES
('Aragorn', 'aragorn@neonark.com', '111-1111', 'KING'),
('Frodo', 'frodo@neonark.com', '222-2222', 'RING BEARER'),
('Samwise', 'samwise@neonark.com', '333-3333', 'RING BEARER BEARER'),
('Pippin', 'pip@neonark.com', '444-4444', 'FOOL'),
('Eowyn', 'eowyn@neonark.com', '555-5555', 'NAZGUL SLAYER');

INSERT INTO observations(creature_id, user_id, note, created_at)
VALUES
(1, 1, 'Creature appears calm and responsive.', NOW()),
(1, 2, 'Feeding completed successfully.', NOW()),
(2, 1, 'Minor aggression observed.', NOW());

INSERT INTO feeding_schedules(creature_id, feeding_time)
VALUES
(1, '08:00'),
(2, '12:00'),
(3, '18:00');