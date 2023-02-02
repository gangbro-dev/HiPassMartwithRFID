import React from "react";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import { Grid, Button } from "@mui/material";

const cardImage = (date) => {
  if (date === "현대") {
    return "/kiosk/images/hyundai.png";
  } else if (date === "ibk") {
    return "/kiosk/images/ibk.png";
  } else if (date === "하나") {
    return "/kiosk/images//hana.png";
  } else if (date === "신한") {
    return "/kiosk/images/shinhan.png";
  } else if (date === "우리") {
    return "/kiosk/images/woori.png";
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

const mainCard = [["4", "1234-5678-9234-2345", "하나", "0124"]];
// 유저 정보에서 card id 뽑아서 카드 목록과 비교 후 저장

// 카드 목록 등록 함수
const renderCards = () => {
  return cards.map((card, index) => {
    return (
      <Grid item margin={1} key={index}>
        <Button
          sx={{ width: "100%", height: "100%", p: 0 }}
          onClick={() => {
            console.log(`${card}`);
          }}
        >
          <CardMedia component="img" image={cardImage(card[2])} />
        </Button>
      </Grid>
    );
  });
};

export default function CardInfo() {
  return (
    <Box style={{width: '100vw', height: '100vh', display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
      <Box style={{ display: 'flex', flexDirection: 'column', height: '100%' }}>


  <Card style={{ border: 1, maxWidth: "500px", textAlign: 'center', minHeight:"400px" }} >
    <CardHeader
            title="메인 카드"
            style={{ textAlign: "center", borderBottom: 1 }}
          />
          <Grid item margin={1}>
            <Card style={{width: '100%', height: '100%'}}>
              <CardMedia
                component="img"
                image={mainCard.length > 0 ? cardImage(mainCard[0][2]) : null}
                style={{width: '100%', height: '100%'}}
              />
            </Card>
          </Grid>
  </Card>



  <Card style={{ border: 1, maxWidth: "500px", textAlign: 'center' }} >
    <CardHeader
            title="내 카드 목록"
            style={{ textAlign: "center", borderBottom: 1 }}
          />
          <div style={{ overflowY: "scroll", height: "100%" }}>
            <Card>{renderCards()}</Card>
          </div>
  </Card>


</Box>

    </Box>





    
  );
}


// {/* <Box>
//       <Grid container spacing={2}>
//         <Grid item xs={1} />
//         <Grid
//           item
//           xs={10}
//           mt={4}
//           sx={{ display: "flex", justifyContent: "center" }}
//         >
//           <Card sx={{ border: 1, maxWidth: "500px" }}>
//             <CardHeader
//               title="메인 카드"
//               sx={{ textAlign: "center", borderBottom: 1 }}
//             />
//             <Grid item margin={1}>
//               <Card>
//                 <CardMedia
//                   component="img"
//                   image={mainCard.length > 0 ? cardImage(mainCard[0][2]) : null}
//                 />
//               </Card>
//             </Grid>
//           </Card>
//         </Grid>
//       </Grid>

//       <Grid container spacing={2}>
//         <Grid item xs={1} />
//         <Grid
//           item
//           xs={10}
//           mt={4}
//           sx={{ display: "flex", justifyContent: "center" }}
//         >
//           <Card sx={{ border: 1, maxWidth: "500px" }}>
//             <CardHeader
//               title="내 카드 목록"
//               sx={{ textAlign: "center", borderBottom: 1 }}
//             />
//             <div style={{ overflowY: "scroll", height: "100%" }}>
//               <Card>{renderCards()}</Card>
//             </div>
//           </Card>
//         </Grid>
//       </Grid>

//       <Grid container spacing={2}>
//         <Grid item xs={1} />
//         <Grid item xs={10} mt={4}>
//           <CssBaseline />
//         </Grid>
//       </Grid>
//     </Box> */}