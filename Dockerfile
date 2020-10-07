FROM openliberty/open-liberty:kernel-java11-openj9-ubi

MAINTAINER Basile Botebol <basile.botebol@gmail.com>

COPY --chown=1001:0 ./src/main/liberty/config/server.xml /config/
COPY --chown=1001:0 ./target/*.war /config/apps


# Run the server script and start the defaultServer by default.
ENTRYPOINT ["/opt/ol/wlp/bin/server", "run"]
CMD ["defaultServer"]


