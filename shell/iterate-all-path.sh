#!/bin/bash

IP="127.0.0.1"
PORT=80
SLUG="slug-test"
RESERVATIONNUMBER="10001"
MENUNUMBER="13"

echo "[Is server ALIVE]";
curl -X GET "http://$IP:$PORT/status/ping"; echo;

echo "\n[세차장관리사장님용]";
echo "세차장승인요청";
curl -H "Content-Type: application/json" -X POST -d '{}' "http://$IP:$PORT/provider/new"; echo;
curl -X GET "http://$IP:$PORT/provider/check-slug/$SLUG"; echo;
curl -X GET "http://$IP:$PORT/provider/$SLUG/approve"; echo;

echo "\n세차예약요청조회";
curl -X GET "http://$IP:$PORT/provider/$SLUG/request"; echo;
curl -X GET "http://$IP:$PORT/provider/$SLUG/request/$RESERVATIONNUMBER"; echo;
curl -X POST -H "Content-Type: application/json" -d '{ "is_accepted": true, "expected_wash_time": "date 객체", "reason": null }' "http://$IP:$PORT/provider/$SLUG/request/$RESERVATIONNUMBER"; echo;

echo "\n세차스케줄조회";
curl -X GET "http://$IP:$PORT/provider/$SLUG/schedule"; echo;
curl -X GET "http://$IP:$PORT/provider/$SLUG/schedule/$RESERVATIONNUMBER"; echo;
curl -X DELETE -H "Content-Type: application/json" -d '{ "reason": "세차를 거부한 이유" }' "http://$IP:$PORT/provider/$SLUG/schedule/$RESERVATIONNUMBER"; echo;

echo "\n운영시간설정";
curl -X POST -H "Content-Type: application/json" -d '{}' "http://$IP:$PORT/provider/$SLUG/time"; echo;
curl -X POST -H "Content-Type: application/json" -d '{}' "http://$IP:$PORT/provider/$SLUG/except"; echo;

echo "\n메뉴관리";
curl -X POST -H "Content-Type: application/json" -d '{}' "http://$IP:$PORT/provider/$SLUG/menu/write"; echo;
curl -X PATCH -H "Content-Type: application/json" -d '{}' "http://$IP:$PORT/provider/$SLUG/menu/$MENUNUMBER"; echo;
curl -X DELETE "http://$IP:$PORT/provider/$SLUG/menu/$MENUNUMBER"; echo;