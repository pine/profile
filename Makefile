PY?=python3
PELICAN?=pelican
PELICANOPTS=

BASEDIR=$(CURDIR)
INPUTDIR=$(BASEDIR)/content
OUTPUTDIR=$(BASEDIR)/public
CONFFILE=$(BASEDIR)/pelicanconf.py


.PHONY: content
content: poetry-install
	PYTHONUNBUFFERED=no poetry run $(PELICAN) $(INPUTDIR) --output $(OUTPUTDIR) --settings $(CONFFILE) -r $(PELICANOPTS)


.PHONY: poetry-install
poetry-install:
	if ! poetry --version; then \
		pip install poetry; \
	fi
	poetry install


.PHONY: clean
clean:
	rm -rf $(OUTPUTDIR)


.PHONY: serve
serve:
	mkdir -p $(OUTPUTDIR)
ifdef PORT
	@echo Serving on http://0.0.0.0:$(PORT)/ ...
	cd $(OUTPUTDIR) && $(PY) -m http.server $(PORT)
else
	@echo Serving on http://0.0.0.0:8000/ ...
	cd $(OUTPUTDIR) && $(PY) -m http.server 8000
endif

