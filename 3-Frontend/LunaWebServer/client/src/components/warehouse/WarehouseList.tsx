import { Button } from "@/components/ui/button";
import { useState } from "react";
import CreateWarehouseDialog from "./CreateWarehouseDialog";

const initialWarehouses = [
  { 
    id: 1, 
    name: "Kho Củ Chi",
    phone: "84 326 158 541",
    email: "luna.cuchi@gmail.com",
    address: "Khu phố 1 - Thị trấn Củ Chi - Huyện Củ Chi - TP. Hồ Chí Minh"
  },
  { id: 2, name: "Kho Quận 10" },
  { id: 3, name: "Kho Quận 1" }
];

export default function WarehouseList() {
  const [warehouses, setWarehouses] = useState(initialWarehouses);
  const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false);

  const handleCreateWarehouse = (newWarehouse: any) => {
    const warehouse = {
      id: warehouses.length + 1,
      ...newWarehouse
    };
    setWarehouses([...warehouses, warehouse]);
  };

  return (
    <div className="space-y-2">
      <h2 className="text-sm font-medium mb-4">DANH SÁCH KHO</h2>
      {warehouses.map((warehouse) => (
        <Button
          key={warehouse.id}
          variant="ghost"
          className="w-full justify-start text-left font-normal"
        >
          {warehouse.name}
        </Button>
      ))}

      <div className="pt-6">
        <h2 className="text-sm font-medium mb-4">QUẢN LÝ THÔNG TIN</h2>
        <div className="space-y-2">
          <Button variant="ghost" className="w-full justify-start text-left font-normal">
            Tổng quan kho
          </Button>
          <Button variant="ghost" className="w-full justify-start text-left font-normal">
            Danh sách sản phẩm
          </Button>
          <Button variant="ghost" className="w-full justify-start text-left font-normal">
            Danh sách phiếu nhập kho
          </Button>
          <Button variant="ghost" className="w-full justify-start text-left font-normal">
            Danh sách phiếu xuất kho
          </Button>
        </div>
      </div>

      <CreateWarehouseDialog
        open={isCreateDialogOpen}
        onOpenChange={setIsCreateDialogOpen}
        onSubmit={handleCreateWarehouse}
      />
    </div>
  );
}