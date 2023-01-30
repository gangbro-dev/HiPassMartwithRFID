import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import { Box, Grid } from "@mui/material";
import { TableVirtuoso } from "react-virtuoso";
import { Card } from "@mui/material";
import axios from "axios";
import HOST from "../../Host";

const sample = [
  ["삼성카드", 159000, "2022/12/21"],
  ["비씨카드", 237000, "2022/12/22"],
  ["현대카드", 262000, "2022/12/27"],
  ["신한카드", 305000, "2023/01/11"],
  ["우리카드", 356000, "2023/01/17"],
];

function createData(id, paymentcard, fullprice, paymentdate) {
  return { id, paymentcard, fullprice, paymentdate };
}

const columns = [
  {
    width: 180,
    label: "결제수단",
    dataKey: "paymentcard",
  },
  {
    width: 120,
    label: "총 결제액",
    dataKey: "fullprice",
    numeric: true,
  },
  {
    width: 120,
    label: "결제일",
    dataKey: "paymentdate",
    numeric: true,
  },
];

const rows = Array.from({ length: 20 }, (_, index) => {
  const randomSelection = sample[Math.floor(Math.random() * sample.length)];
  return createData(index, ...randomSelection);
});

const VirtuosoTableComponents = {
  Scroller: React.forwardRef((props, ref) => (
    <TableContainer component={Paper} {...props} ref={ref} />
  )),
  Table: (props) => <Table {...props} style={{ borderCollapse: "separate" }} />,
  TableHead,
  TableRow: ({ item: _item, ...props }) => <TableRow {...props} />,
  TableBody: React.forwardRef((props, ref) => (
    <TableBody {...props} ref={ref} />
  )),
};

function fixedHeaderContent() {
  return (
    <TableRow>
      {columns.map((column) => (
        <TableCell
          key={column.dataKey}
          variant="head"
          align={column.numeric || false ? "right" : "left"}
          style={{ width: column.width }}
          sx={{
            backgroundColor: "#90caf9",
            fontWeight: "bold",
            fontSize: 18,
            borderBottom: 1,
          }}
        >
          {column.label}
        </TableCell>
      ))}
    </TableRow>
  );
}

function rowContent(_index, row) {
  return (
    <React.Fragment>
      {columns.map((column) => (
        <TableCell
          key={column.dataKey}
          align={column.numeric || false ? "right" : "left"}
          sx={{ fontWeight: "bold" }}
        >
          {row[column.dataKey]}
        </TableCell>
      ))}
    </React.Fragment>
  );
}

const PaymentList = async (data) => {
  // formdata 처리
  

  // get
  console.log("paymentlist")
  const API_URI = `${HOST}/api/user/{userid}/purchase`;
  await axios
    .get(API_URI)
    .then((response) => {
      // TODO 결제 내역 리스트 처리 로직

    })
    .catch(function (err) {
      console.log(err);
    });
};

export default function Payment() {
  PaymentList();
  return (
    <Box>
      <Grid container spacing={2}>
        <Grid />
        <Grid item xs={12}>
          <Card
            sx={{
              fontSize: 33,
              padding: 2,
              textAlign: "center",
              backgroundColor: "#64b5f6",
              fontWeight: "bold",
            }}
          >
            결제내역
          </Card>
          <Paper style={{ height: "84vh", width: "100%" }}>
            <TableVirtuoso
              data={rows}
              components={VirtuosoTableComponents}
              fixedHeaderContent={fixedHeaderContent}
              itemContent={rowContent}
            />
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
}
