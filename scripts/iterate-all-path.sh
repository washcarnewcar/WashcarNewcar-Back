#!/bin/sh

IP="127.0.0.1"
PORT=80
SLUG="slug-test"
RESERVATIONNUMBER="10001"
MENUNUMBER="13"
ID="testid"
LONGITUDE="126.7059347817178"
LATITUDE="37.4527602629939"
DATE="YYYY-MM-DD"
PHONENUMBER="01010002000"
BRANDNUMBER="50"

echo "[Is server ALIVE]";
curl -X GET "http://$IP:$PORT/status/ping"; echo;

echo "\n[사용자인증]";
curl -X POST -H "Content-Type: application/json" -d '{}' "http://$IP:$PORT/login"; echo;
curl -X GET "http://$IP:$PORT/signup/check?id=$ID"; echo;
curl -X POST -H "Content-Type: application/json" -d '{}' "http://$IP:$PORT/signup"; echo;

echo "\n[세차장검색]";
curl -X GET "http://$IP:$PORT/search/store?longitude=$LONGITUDE&latitude=$LATITUDE"; echo;
curl -X GET "http://$IP:$PORT/store/$SLUG/info"; echo;
curl -X GET "http://$IP:$PORT/store/$SLUG/menu"; echo;
curl -X GET "http://$IP:$PORT/store/$SLUG/detail"; echo;

echo "\n[세차예약]";
curl -X GET "http://$IP:$PORT/store/$SLUG/menu/$MENUNUMBER"; echo;
curl -X GET "http://$IP:$PORT/store/$SLUG/menu/$MENUNUMBER/available"; echo;
curl -X GET "http://$IP:$PORT/store/$SLUG/menu/$MENUNUMBER/available/$DATE"; echo;
curl -X POST -H "Content-Type: application/json" -d '{}' "http://$IP:$PORT/store/$SLUG/menu/$MENUNUMBER"; echo;

echo "\n[세차예약확인]";
curl -X GET "http://$IP:$PORT/find/$PHONENUMBER"; echo;
curl -X GET "http://$IP:$PORT/reservation/$RESERVATIONNUMBER"; echo;

echo "\n[세차장관리사장님용]";
echo "세차장승인요청";
curl -X POST -H "Content-Type: application/json" -d '{}' "http://$IP:$PORT/provider/new"; echo;
curl -X POST -H "Content-Type: application/json" -d '{}' "http://$IP:$PORT/provider/$SLUG/store"; echo;
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

echo "\n[차량정보]";
curl -X GET "http://$IP:$PORT/car/brand"; echo;
curl -X GET "http://$IP:$PORT/car/brand/$BRANDNUMBER"; echo;
