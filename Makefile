COMPOSER_BIN := bin/composer.phar
.PHONY: install

install:
	php $(COMPOSER_BIN) install

reload:
	php $(COMPOSER_BIN) dump-autoload

server:
	php -S 0.0.0.0:8000 -t public

lint:
	npm install
	npm test
