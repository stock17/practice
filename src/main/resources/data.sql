INSERT INTO Document_type (code, version, name) VALUES
    (3,  0, 'Свитедельство о рождении'),
    (7,  0, 'Военный билет'),
    (8,  0, 'Временное удостоверение, выданное взамен военного билета'),
    (10, 0, 'Паспорт иностранного гражданина'),
    (11, 0, 'Свидетельство о рассмотрении ходатайства о признании лица беженцем на территории Российской Федерации по существу'),
    (12, 0, 'Вид на жительство в Российской Федерации'),
    (13, 0, 'Удостоверение беженца'),
    (15, 0, 'Разрешение на временное проживание в Российской Федерации'),
    (18, 0, 'Свитедельство о предоставлении временного убежища на территории Российской Федерации'),
    (21, 0, 'Паспорт гражданина Российской Федерации'),
    (23, 0, 'Свидетельство о рождении, выданное уполномоченным органом иностранного государства '),
    (24, 0, 'Удостоверение личности военнослужащего Российской Федерации'),
    (91, 0, 'Иные документы')
;


INSERT INTO Country (code, version, name) VALUES
    (10,  0, 'Антарктида'),
    (31,  0, 'Азербайджан'),
    (51,  0, 'Армения'),
    (112, 0, 'Беларусь'),
    (124, 0, 'Канада'),
    (156, 0, 'Китай'),
    (233, 0, 'Эстония'),
    (268, 0, 'Грузия'),
    (276, 0, 'Германия'),
    (340, 0, 'Гондурас'),
    (356, 0, 'Индия'),
    (616, 0, 'Польша'),
    (643, 0, 'Россия'),
    (646, 0, 'Руанда'),
    (762, 0, 'Таджикистан'),
    (764, 0, 'Тайланд'),
    (826, 0, 'Великобритания'),
    (840, 0, 'США') 
;

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES
    (1, 0, 'ООО УльтраПрогер', 'Общество с ограниченной ответственностью Ультрапрогер', 1564589815, 166001001, 'г. Уфа, ул. Ленина, 118', '8-800-555-35-35', TRUE),
    (2, 0, 'ООО Индастриал Нейчер', 'Общество с ограниченной ответственностью Индастриал Нейчер', 6587942415, 166001001, 'Саратовская область, д. Свиридово, ул. Зеленая, 1',
     '8-800-128-16-18', TRUE),
    (3, 0, 'ООО Лос поллос', 'Общество с ограниченной ответственностью Лос Поллос', 1759648465, 166001001, 'г. Москва, ул. Академика Королева, 19', '8-800-245-16-18', TRUE)
;


INSERT INTO Office (id, version, name, address, phone, is_active, organization_id) VALUES
    (1, 0, 'Главный офис',       'г. Уфа, ул. Ленина, 118',                           '8-800-128-16-18', TRUE, 1),
    (2, 0, 'Мобильный офис',     'г. Уфа, ул. Советская, 47',                         '8-800-114-17-20', TRUE, 1),  
    (3, 0, 'Отдел тестирования', 'г. Уфа, ул. Казанская, 96',                         '8-800-987-65-43', TRUE, 1),  
    (4, 0, 'Центральный офис',   'Саратовская область, д. Свиридово, ул. Зеленая, 1', '8-800-128-16-18', TRUE, 2),
    (5, 0, 'Дирекция',           'г. Москва, ул. Академика Королева, 19',             '8-800-245-16-18', TRUE, 3)
;


INSERT INTO Document (id, version, doc_number, doc_date, doc_code) VALUES
    (1,  0, 9907589458, '2000-01-28', 21),
    (2,  0, 9902456123, '2002-03-15', 21),
    (3,  0, 9905181245, '2005-07-07', 21),
    (4,  0, 9910759632, '2010-04-12', 21),
    (5,  0, 9902658794, '2002-10-11', 21),
    (6,  0, 9915985423, '2015-06-02', 21),
    (7,  0, 7308325486, '2008-05-16', 21),
    (8,  0, 4506245698, '2006-08-24', 21),
    (9,  0, 1913397,    '1999-09-30', 10),
    (10, 0, 5978546,    '1995-02-27', 10)   
;


INSERT INTO User (id, version, first_name, middle_name, second_name, position, phone, address, is_identified, office_id, document_id, citizenship) VALUES
    (1,  0, 'Петр',         'Сергеевич',     'Филькин',  'Директор',        '65-45-89',     'г. Уфа, ул. Промышленная, 18',           TRUE, 1, 1,  643),
    (2,  0, 'Александр',    'Петрович',      'Синичкин', 'ИТ-директор',     '65-68-17',     'г. Уфа, ул. Золотников, 124-4',          TRUE, 1, 2,  643),
    (3,  0, 'Игорь',        'Федорович',     'Стасов',   'Тим-лид',         '+79378546599', 'г. Уфа, ул. Красносельская, 8-24',       TRUE, 2, 3,  643),
    (4,  0, 'Иван',         'Алексеевич',    'Сергеев',  'Разработчик',     '+79371234567', 'г. Уфа, ул. Зеленая, 13-12',             TRUE, 2, 4,  643),
    (5,  0, 'Алексей',      'Геннадьевич',   'Прохоров', 'QA Automation',   '+79561542336', 'г. Уфа, ул. Комсомольская, 1-15',        TRUE, 3, 5,  643),
    (6,  0, 'Степан',       'Аркадьевич',    'Леваков',  'QA Automation',   '+79171563278', 'г. Уфа, ул. Комсомольская, 1-16',        TRUE, 3, 6,  643),
    (7,  0, 'Антон',        'Юрьевич',       'Ковалев',  'Директор',        '+79084562189', 'г. Саратов, ул. Свирская, 36',           TRUE, 4, 7,  643),
    (8,  0, 'Велен',        'Станиславович', 'Ежов',     'Разработчик',     '+79536589547', 'г. Саратов, ул. Конечная, 38-5',         TRUE, 4, 8,  643),
    (9,  0, 'Пол',          NULL,            'Маккартни','Директор',        '+44561452601', 'г. Лондон,  Бейкер стрит, 221б',         TRUE, 5, 9,  826),
    (10, 0, 'Дональд',      NULL,            'Трамп',    'PR-Директор',     '+18561452658', 'г. Вашингтон, Пенсильвания авеню, 1600', TRUE, 5, 10, 840) 
;