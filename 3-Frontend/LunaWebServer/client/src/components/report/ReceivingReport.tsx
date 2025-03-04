import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Card } from "@/components/ui/card";

interface ReceivingItem {
  id: number;
  date: string;
  receiptNumber: string;
  productName: string;
  quantity: number;
  condition: string;
  inspector: string;
}

const mockReceivings: ReceivingItem[] = [
  {
    id: 1,
    date: "2024-02-27",
    receiptNumber: "RCV-001",
    productName: "Nike Air Max 270",
    quantity: 100,
    condition: "Perfect",
    inspector: "Nguyễn Văn A"
  },
  {
    id: 2,
    date: "2024-02-26",
    receiptNumber: "RCV-002",
    productName: "Adidas Ultraboost",
    quantity: 75,
    condition: "Minor Damage",
    inspector: "Trần Thị B"
  }
];

export default function ReceivingReport() {
  return (
    <div className="space-y-6">
      <div className="grid grid-cols-3 gap-4">
        <Card className="p-4">
          <div className="text-sm text-muted-foreground">Tổng số lô hàng đã nhận</div>
          <div className="text-2xl font-bold">156 lô</div>
        </Card>
        <Card className="p-4">
          <div className="text-sm text-muted-foreground">Tỷ lệ hàng hoàn hảo</div>
          <div className="text-2xl font-bold">98.5%</div>
        </Card>
        <Card className="p-4">
          <div className="text-sm text-muted-foreground">Thời gian xử lý trung bình</div>
          <div className="text-2xl font-bold">45 phút</div>
        </Card>
      </div>

      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>Ngày</TableHead>
            <TableHead>Số phiếu</TableHead>
            <TableHead>Sản phẩm</TableHead>
            <TableHead>Số lượng</TableHead>
            <TableHead>Tình trạng</TableHead>
            <TableHead>Người kiểm hàng</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {mockReceivings.map((item) => (
            <TableRow key={item.id}>
              <TableCell>{item.date}</TableCell>
              <TableCell>{item.receiptNumber}</TableCell>
              <TableCell>{item.productName}</TableCell>
              <TableCell>{item.quantity}</TableCell>
              <TableCell>{item.condition}</TableCell>
              <TableCell>{item.inspector}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
}
