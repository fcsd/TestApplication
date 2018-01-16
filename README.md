# TestApplication
This app is sample of implementation our architecture. Architecture implements “Layered design” conceptions ( msdn.microsoft.com/en-us/library/ee658109.aspx ).

“Presentation layer“ represents UI elements (Activities, Fragments, View and so) and adapters for them.

“Data layer“ realize logic working with data. It contains request, datasources, models, api and so. The api incapsulate endpoint parameters. The model incapsulate the data. The data source has request (or requests inside) to load data by appropriate endpoint. The request incapsulate loading and parsing the data in the model.

“Bisness layer“ usually has 'flow' class, which responsible for business logic on screen.
