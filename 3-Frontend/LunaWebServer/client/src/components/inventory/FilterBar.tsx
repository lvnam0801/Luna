import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Search, SlidersHorizontal } from "lucide-react";

interface FilterBarProps {
  onSearch: (value: string) => void;
  onFilterChange: (filter: string, value: string) => void;
}

export default function FilterBar({ onSearch, onFilterChange }: FilterBarProps) {
  return (
    <div className="space-y-4">
      <div className="flex gap-4">
        <div className="flex-1 relative">
          <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-500 h-4 w-4" />
          <Input 
            placeholder="Tìm kiếm sản phẩm" 
            className="pl-10"
            onChange={(e) => onSearch(e.target.value)}
          />
        </div>
        <Button variant="outline" className="gap-2">
          <SlidersHorizontal className="h-4 w-4" />
          Lọc
        </Button>
      </div>
      
      <div className="flex gap-4">
        <Select onValueChange={(value) => onFilterChange('status', value)}>
          <SelectTrigger className="w-[180px]">
            <SelectValue placeholder="Trạng thái sản phẩm" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="all">Tất cả</SelectItem>
            <SelectItem value="active">Đang hoạt động</SelectItem>
            <SelectItem value="inactive">Không hoạt động</SelectItem>
          </SelectContent>
        </Select>

        <Select onValueChange={(value) => onFilterChange('category', value)}>
          <SelectTrigger className="w-[180px]">
            <SelectValue placeholder="Danh mục" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="all">Tất cả danh mục</SelectItem>
            <SelectItem value="shoes">Giày dép</SelectItem>
            <SelectItem value="clothing">Quần áo</SelectItem>
            <SelectItem value="accessories">Phụ kiện</SelectItem>
          </SelectContent>
        </Select>

        <Select onValueChange={(value) => onFilterChange('price', value)}>
          <SelectTrigger className="w-[180px]">
            <SelectValue placeholder="Mức giá" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="all">Tất cả mức giá</SelectItem>
            <SelectItem value="0-100">0 - 100.000đ</SelectItem>
            <SelectItem value="100-300">100.000đ - 300.000đ</SelectItem>
            <SelectItem value="300+">Trên 300.000đ</SelectItem>
          </SelectContent>
        </Select>
      </div>
    </div>
  );
}
