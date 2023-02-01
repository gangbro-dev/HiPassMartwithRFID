import React from "react";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import { Grid } from "@mui/material";
import { useNavigate } from "react-router-dom";

// "cardNo" : "1234-5678-9234-2345",
// 		"isDefault" : false,
// 		"name": "신한",
// 		"validDate": "0124",

const cardImage = (date) => {
  if (date === "현대") {
    return "/app/images/hyundai.png";
  } else if (date === "ibk") {
    return "/app/images/ibk.png";
  } else if (date === "하나") {
    return "/app/images//hana.png";
  } else if (date === "신한") {
    return "/app/images/shinhan.png";
  } else if (date === "우리") {
    return "/app/images/woori.png";
  } else {
    return null;
  }
};

// 카드 목록 리스트 - 카드정보 통신을 통해 값을 받아와야함
const cards = [
  ["1", "1234-5678-9234-2345", "신한", "0124"],
  ["2", "1234-5678-9234-2345", "현대", "0124"],
  ["3", "1234-5678-9234-2345", "ibk", "0124"],
  ["4", "1234-5678-9234-2345", "하나", "0124"],
  ["5", "1234-5678-9234-2345", "우리", "0124"],
];

const mainCard = []
// 유저 정보에서 card id 뽑아서 카드 목록과 비교 후 저장


// 카드 목록 등록 함수
const renderCards = () => {
  return cards.map((card, index) => {
    return (
      <Grid item margin={1} key={index}>
        <Card sx={{ mx:2 }}>
          <CardMedia
            component="img"
            image={cardImage(card[2])}
            onClick={() => {
              console.log(`${card}`)
          }}
          />
        </Card>
      </Grid>
    );
  });
};

export default function CardInfo() {
  const navigate = useNavigate();

  return (
    <Box sx={{ pb: 7 }}>
      <Grid container spacing={2}>
        <Grid item xs={1} />
        <Grid item xs={10} mt={4}>
          <CssBaseline />
          <Card sx={{ border: 1 }}>
            <CardHeader title="메인 카드" sx={{ textAlign: "center", borderBottom:1}} />
            <Grid item margin={1}>
              <Card sx={{  mx:2 }}>
              <CardMedia
                component="img"
                image={mainCard.length > 0 ? cardImage(mainCard[0][2]) : null}
              />
              </Card>
              </Grid>
          </Card>
        </Grid>
      </Grid>
      <Grid container spacing={2}>
        <Grid item xs={1} />
        <Grid item xs={10} mt={4}>
          <CssBaseline />
          <Card sx={{ border: 1 }}>
            <CardHeader
              title="카드 목록"
              sx={{ textAlign: "center", borderBottom: 1 }}
            />
            <Card>{renderCards()}
            <Grid item margin={3}>
            <Card sx={{ mx:2}}>
            <CardMedia
            id="addCard"
            component="img"
            image="/app/images/addCard.png"
            onClick={() => {
              window.location.href="/app/addcard";
          }}
          />
            </Card>
          </Grid>
          </Card>
          </Card>
        </Grid>
      </Grid>
      <Grid container spacing={2}>
        <Grid item xs={1} />
        <Grid item xs={10} mt={4}>
          <CssBaseline />
        </Grid>
      </Grid>
    </Box>
  );
}
