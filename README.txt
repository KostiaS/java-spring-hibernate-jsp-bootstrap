1. Spring configuration files: resources/META-INF/spring
	Implementation of storaging data can be change in beans.xml

2. Hibernate persistence.xml: resources/META-INF
	Database name: testwork-ksalimov
	username: root
	password: ""
3. Web resources: web/resources
	CSS: ../css
	Bootstrap: ../lib

	index.jsp - Main view
	create-edit-message.jsp - Create/Edit View
	processing-create-edit-message-validation.jsp - validation process. After validation forwards to index.jsp if success or to retry.jsp if fail
	retry.jsp - Create/Edit View with error messages
	