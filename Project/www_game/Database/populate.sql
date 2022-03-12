-- CALL question_insert_procedure (
--                             TEXT 'Question1'
--                           );
--
-- CALL question_insert_procedure (
--                             TEXT 'Question2'
--                           );
--
--
-- CALL question_insert_procedure (
--                             TEXT 'Question3'
--                           );
--

CALL user_insert_procedure(Text 'Buran', Text 'Buran_password');

CALL pack_insert_procedure(TEXT 'Buran''s pack', BIGINT '1');