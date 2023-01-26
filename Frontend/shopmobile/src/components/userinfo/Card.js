import React from "react";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import { Grid } from "@mui/material";

export default function CardInfo() {
  return (
    <Box sx={{ pb: 7 }}>
      <Grid container spacing={2}>
        <Grid item xs={1} />
        <Grid item xs={10} mt={4}>
          <CssBaseline />
          <Card sx={{ border: 1 }}>
            <CardHeader
              title="등록 카드"
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
