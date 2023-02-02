import * as React from "react";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Card from "@mui/material/Card";
import Typography from "@mui/material/Typography";
import CardActions from "@mui/material/CardActions";
import Button from "@mui/material/Button";
import { useNavigate } from "react-router-dom";

const columns = [
  { id: "name", label: "품명", minWidth: 140 },
  { id: "price", label: "단가", minWidth: 50 },
  {
    id: "cnt",
    label: "수량",
    minWidth: 70,
    align: "right",
  },
  {
    id: "fullprice",
    label: "총액",
    minWidth: 70,
    align: "right",
  },
];

function createData(name, price, cnt) {
  const fullprice = price * cnt;
  return { name, price, cnt, fullprice };
}

const rows = [
  createData("폰트크기 바꿧지롱", 1500, 5),
  createData("동원참치", 4000, 1),
  createData("???", 10000, 3),
  createData("????", 5000, 4),
  createData("?????", 4000, 1),
  createData("??????", 5000, 2),
  createData("??", 12000, 5),
  createData("???", 8000, 6),
  createData("????", 12000, 2),
  createData("?????", 400, 3),
  createData("??????", 2400, 4),
  createData("???????", 500, 5),
];

const lstStyle = {
  fontSize: "24px",
};

function UserTrue() {
  const navigate = useNavigate();
  return (
    <Button
      variant="contained"
      onClick={() => navigate("/kiosk/cardcheck")}
      sx={{
        position: "absolute",
        right: 0,
        fontSize: 30,
        fontWeight: "bold",
        margin: 1,
      }}
    >
      결제하기
    </Button>
  );
}

function UserFalse() {
  const navigate = useNavigate();
  return (
    <Button
      variant="contained"
      onClick={() => navigate("/kiosk/cardpayment")}
      sx={{
        position: "absolute",
        right: 0,
        fontSize: 30,
        fontWeight: "bold",
        margin: 1,
      }}
    >
      비회원결제
    </Button>
  );
}

export default function ItemList() {
  var paymentAll = 0;
  const navigate = useNavigate();
  const user = sessionStorage.getItem("user");

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <Box>
        <Card
          sx={{
            fontSize: 40,
            padding: 2,
            textAlign: "center",
            backgroundColor: "#ff8c8c",
            fontWeight: "bold",
          }}
        >
          장바구니
        </Card>
        <Card sx={{ width: `100vw`, border: 1 }}>
          <Paper sx={{ width: "100%", overflow: "hidden" }}>
            <TableContainer sx={{ minHeight: `73vh`, maxHeight: `50vh` }}>
              <Table stickyHeader aria-label="sticky table">
                <TableHead>
                  <TableRow>
                    {columns.map((column) => (
                      <TableCell
                        key={column.id}
                        align={column.align}
                        style={lstStyle}
                        sx={{ backgroundColor: "#90caf9", fontWeight: "bold" }}
                      >
                        {column.label}
                      </TableCell>
                    ))}
                  </TableRow>
                </TableHead>
                <TableBody>
                  {rows.map((row) => {
                    paymentAll = row.fullprice + paymentAll;
                    return (
                      <TableRow
                        hover
                        role="checkbox"
                        tabIndex={-1}
                        key={row.code}
                      >
                        {columns.map((column) => {
                          const value = row[column.id];
                          return (
                            <TableCell
                              key={column.id}
                              align={column.align}
                              style={lstStyle}
                              sx={{ fontWeight: "bold" }}
                            >
                              {column.format && typeof value === "number"
                                ? column.format(value)
                                : value}
                            </TableCell>
                          );
                        })}
                      </TableRow>
                    );
                  })}
                </TableBody>
              </Table>
            </TableContainer>
            <Box sx={{ borderTop: 1 }}>
              <Typography
                sx={{
                  fontSize: 33,
                  fontWeight: "bold",
                  color: "red",
                  textAlign: "center",
                }}
              >
                다음 상품은 바코드로 결제해주세요
              </Typography>
            </Box>
            <CardActions sx={{ borderTop: 1 }}>
              <Typography sx={{ fontSize: 35, fontWeight: "bold", margin: 1 }}>
                총 결제금액
              </Typography>
              <Typography
                sx={{
                  position: "absolute",
                  right: "6%",
                  fontSize: 30,
                  fontWeight: "bold",
                }}
              >
                {paymentAll}원
              </Typography>
            </CardActions>
          </Paper>
          <Button
            color="error"
            sx={{ fontSize: 30, margin: 1 }}
            variant="contained"
            onClick={() => navigate("/kiosk")}
          >
            결제취소
          </Button>
          <Button
            
            variant="outlined"
            sx={{ fontSize: 30, margin: 1}}
            onClick={() => navigate("/kiosk/rfidread")}
          >
            물품 다시찍기
          </Button>
          {user === "user" ? UserTrue() : UserFalse()}
        </Card>
      </Box>
    </Box>
  );
}
