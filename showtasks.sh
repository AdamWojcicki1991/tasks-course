#!/usr/bin/env bash

export RUNCRUD_HOME=~/Projekty/Kodilla/tasks/runcrud.sh

open_browser_with_application() {
  if open -a "google chrome" http://localhost:8080/crud/v1/task/getTasks; then
    echo "Browser with server application successfully open!"
  else
    echo "Cannot open browser with server application!"
  fi
}

fail() {
  echo "There were errors while running runcrud script!"
}

end() {
  echo "Showtasks script is finished!"
}

if $RUNCRUD_HOME; then
  open_browser_with_application
  end
else
  fail
fi
