INSERT INTO menu_service.kitchen ("name") VALUES
                                              ('Пиццы'),
                                              ('Супы'),
                                              ('Бургеры');

INSERT INTO menu_service.dish ("name",kitchen_id,price) VALUES
                                                            ('Гавайская',1,250.0),
                                                            ('Сырная',1,200.0),
                                                            ('Маргарита',1,180.0),
                                                            ('Борщ',2,150.0),
                                                            ('Щи',2,100.0),
                                                            ('Том ям',2,180.0),
                                                            ('Гамбургер',3,100.0),
                                                            ('Чизбургер',3,120.0),
                                                            ('Биг мак',3,150.0);