#!/bin/bash
BASEDIR=$(dirname $0)
DATABASE=eldroW
createdb -U postgres $DATABASE &&
psql -U postgres -d $DATABASE -f "$BASEDIR/eldroW-setup-nousers.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/solutions_and_guesses.sql"
