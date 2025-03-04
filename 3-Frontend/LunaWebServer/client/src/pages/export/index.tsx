import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Plus } from "lucide-react";
import ExportReceiptList from "@/components/export/ExportReceiptList";
import FilterBar from "@/components/shared/FilterBar";

export default function Export() {
  const [searchQuery, setSearchQuery] = useState("");
  const [filters, setFilters] = useState({
    warehouse: "all",
    status: "all"
  });
  const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false);

  const handleSearch = (value: string) => {
    setSearchQuery(value);
  };

  const handleFilterChange = (filterType: string, value: string) => {
    setFilters(prev => ({
      ...prev,
      [filterType]: value
    }));
  };

  return (
    <main className="container mx-auto px-4 py-6">
      <div className="flex justify-between items-center mb-6">
        <div className="flex items-center space-x-2">
          <h1 className="text-2xl font-bold">Kho hàng</h1>
          <div className="text-sm text-muted-foreground">3 kho hàng</div>
        </div>
        <Button onClick={() => setIsCreateDialogOpen(true)}>
          <Plus className="h-4 w-4 mr-2" />
          Thêm đơn xuất
        </Button>
      </div>

      <div className="grid grid-cols-12 gap-6">
        <div className="col-span-3">
          <h2 className="text-sm font-medium mb-4">THÔNG KÊ</h2>
          <div className="space-y-4">
            <div className="bg-white p-4 rounded-lg border">
              <div className="text-sm text-muted-foreground">Tổng giá trị đã xuất:</div>
              <div className="text-2xl font-bold">250M VND</div>
            </div>
            <div className="bg-white p-4 rounded-lg border">
              <div className="text-sm text-muted-foreground">Tổng số phiếu xuất:</div>
              <div className="text-2xl font-bold">10</div>
            </div>
            <div className="bg-white p-4 rounded-lg border">
              <div className="text-sm text-muted-foreground">Tổng số lượng sản phẩm:</div>
              <div className="text-2xl font-bold">120000</div>
            </div>
          </div>
        </div>

        <div className="col-span-9">
          <FilterBar 
            onSearch={handleSearch}
            onFilterChange={handleFilterChange}
          />
          <div className="mt-6">
            <ExportReceiptList 
              searchQuery={searchQuery}
              filters={filters}
              isCreateDialogOpen={isCreateDialogOpen}
              onCreateDialogChange={setIsCreateDialogOpen}
            />
          </div>
        </div>
      </div>
    </main>
  );
}