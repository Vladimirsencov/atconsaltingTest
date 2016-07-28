SET SCHEMA AT_CONS_TEST;

INSERT INTO USERS
SET USERNAME = 'PETR_IVANOV', PASSWORD = '1235', EMAIL = 'petr_ivanov@yandex.ru';
INSERT INTO USERS
SET USERNAME = 'IVAN_PETROV', PASSWORD = '1234', EMAIL = 'ivan_petrov@yandex.ru';
INSERT INTO USERS
SET USERNAME = 'MICHAIL_KONEV', PASSWORD = '1535', EMAIL = 'michail_konev@gmail.com';
INSERT INTO USERS
SET USERNAME = 'NICKOLAY_VODKIN', PASSWORD = '1237', EMAIL = 'nickolay_vodkin@yandex.ru';
INSERT INTO USERS
SET USERNAME = 'PETR NICKOLAYEV', PASSWORD = '1238', EMAIL = 'petr_nicklolaev@gmail.com';


INSERT INTO USER_ROLES
SET USER_ID = 1, ROLE = 'user';

INSERT INTO USER_ROLES
SET USER_ID = 2, ROLE = 'user';

INSERT INTO USER_ROLES
SET USER_ID = 3, ROLE = 'user';

INSERT INTO USER_ROLES
SET USER_ID = 4, ROLE = 'user';

INSERT INTO USER_ROLES
SET USER_ID = 5, ROLE = 'user';

INSERT INTO BOOKS
SET ISBN = '978-5-8189-1095-6', TITLE = 'Последний венгерский король', AUTHOR_NAME = 'Леопольд Захер-Мазок';

INSERT INTO BOOKS
SET ISBN      = '978-5-7035-2113-7', TITLE = 'Электропривод летательных аппаратов',
  AUTHOR_NAME = 'Полковников В.А., Петров Б.И., Рывкин С.Е.';

INSERT INTO BOOKS
SET ISBN      = '5-217-010112-6', TITLE = 'Автоматизированный электропривод станков и промышленных роботов',
  AUTHOR_NAME = 'О.П Михайлов';

INSERT INTO BOOKS
SET ISBN = '978-5-9556-0082-6', TITLE = 'Введение в цифровую схемотехнику', AUTHOR_NAME = 'Ю. В. Новиков';

INSERT INTO BOOKS
SET ISBN = '978-5-9775-0162-0', TITLE = 'Цифровая схемотехника', AUTHOR_NAME = 'Угрюмов Е.П.';

INSERT INTO BOOKS
SET ISBN      = '5-283-04468-8', TITLE = 'Примеры расчета автоматизированног электропривода на ЭВМ',
  AUTHOR_NAME = 'А.В. Башарин, Ю.В. Постников';
INSERT INTO BOOKS
SET ISBN = '978-58118-5115-5', TITLE = 'США: Истроия география и традиции', AUTHOR_NAME = 'Галина Бардина';




