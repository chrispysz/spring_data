Architektura
================================

Logowanie
------------------------------

.. uml::

    User -> UserService: Authentication Request
    UserService --> User: Authentication Response

	
Zapytania klienta
------------------------------

.. uml::

    Customer -> CustomerApi: Get Request
    CustomerApi --> Customer: Get Response
