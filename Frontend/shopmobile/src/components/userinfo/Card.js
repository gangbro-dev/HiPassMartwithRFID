// https://codesandbox.io/s/react-vertical-carousel-rwop7?file=/src/Slide.js:146-161
import React from "react";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import { Grid } from "@mui/material";

const sample = [
  ["삼성카드", 159000, "2022/12/21"],
  ["비씨카드", 237000, "2022/12/22"],
  ["현대카드", 262000, "2022/12/27"],
  ["신한카드", 305000, "2023/01/11"],
  ["우리카드", 356000, "2023/01/17"],
];


export default function CardInfo() {
  return (
    <Box sx={{ pb: 7 }}>
      <Grid container spacing={2}>
        <Grid item xs={1} />
        <Grid item xs={10} mt={4}>
          <CssBaseline />
          <Card sx={{ border: 1 }}>
            <CardHeader
              title="메인 카드"
              sx={{ textAlign: "center" }}
            />
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
              sx={{ textAlign: "center" }}
            />
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
}
