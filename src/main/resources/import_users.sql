INSERT INTO public.users(id, first_name, login, password, role_id) VALUES (1, 'Jack', 'admin', '$2a$10$ceh2nV.gNAZHB51LMTmo6OcmVZiwvZyzhPQ5m4kRQ.vYpPuJCi/xa', 1), (2, 'Carl', 'user',  '$2a$10$8xerYR2XmHtRASJX9l2Hju0QZ/Q2Sib4JryRL6/z4993tpiAeXqYm', 2) ON CONFLICT (id) DO NOTHING;