import { useState } from "react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Search, Plus } from "lucide-react";
import WarehouseList from "@/components/warehouse/WarehouseList";
import WarehouseOverview from "@/components/warehouse/WarehouseOverview";

export default function Warehouse() {
  const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false);

  return (
    <main className="container mx-auto px-4 py-6">
      <div className="flex justify-between items-center mb-6">
        <div className="flex items-center space-x-2">
          <h1 className="text-2xl font-bold">Kho hàng</h1>
          <div className="text-sm text-muted-foreground">3 kho hàng</div>
        </div>
        <div className="flex items-center space-x-4">
          <div className="relative w-64">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-500 h-4 w-4" />
            <Input placeholder="Tìm kiếm sản phẩm" className="pl-10" />
          </div>
          <Button onClick={() => setIsCreateDialogOpen(true)}>
            <Plus className="h-4 w-4 mr-2" />
            Thêm kho mới
          </Button>
        </div>
      </div>

      <div className="grid grid-cols-12 gap-6">
        <div className="col-span-3">
          <WarehouseList isCreateDialogOpen={isCreateDialogOpen} onCreateDialogChange={setIsCreateDialogOpen} />
        </div>
        <div className="col-span-9">
          <WarehouseOverview />
        </div>
      </div>
    </main>
  );
}