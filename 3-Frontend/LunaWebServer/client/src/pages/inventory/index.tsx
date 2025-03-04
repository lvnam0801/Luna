import { useState } from "react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Search, Plus } from "lucide-react";
import ProductTable from "@/components/inventory/ProductTable";
import FilterBar from "@/components/inventory/FilterBar";

export default function Inventory() {
  const [searchQuery, setSearchQuery] = useState("");
  const [filters, setFilters] = useState({
    status: "all",
    category: "all",
    price: "all"
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
        <h1 className="text-2xl font-bold">QUẢN LÝ SẢN PHẨM</h1>
        <Button onClick={() => setIsCreateDialogOpen(true)}>
          <Plus className="h-4 w-4 mr-2" />
          Thêm sản phẩm
        </Button>
      </div>

      <div className="space-y-6">
        <FilterBar 
          onSearch={handleSearch}
          onFilterChange={handleFilterChange}
        />
        <ProductTable 
          searchQuery={searchQuery}
          filters={filters}
          isCreateDialogOpen={isCreateDialogOpen}
          onCreateDialogChange={setIsCreateDialogOpen}
        />
      </div>
    </main>
  );
}