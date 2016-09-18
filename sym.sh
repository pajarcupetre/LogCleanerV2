#!/bin/bash

COUNTER=0
while [  $COUNTER -lt 10 ]; do
  echo The counter is $COUNTER
  tail -f /var/log/system.log > system.out &
  sleep 30
  ps -a | grep tail | grep system | awk '{print $1}' | xargs kill -9
  mv system.out system${COUNTER}.out
  let COUNTER=COUNTER+1
done
