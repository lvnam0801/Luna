import { useState } from "react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Search } from "lucide-react";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";

interface FilterBarProps {
  onSearch: (value: string) => void;
  onFilterChange: (type: string, value: string) => void;
}

export default function FilterBar({ onSearch, onFilterChange }: FilterBarProps) {
  const [searchQuery, setSearchQuery] = useState("");

  const handleSearch = () => {
    onSearch(searchQuery);
  };

  return (
    <div className="flex gap-4 items-center">
      <div className="flex-1 relative">
        <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-500 h-4 w-4" />
        <Input 
          placeholder="Tìm kiếm theo mã phiếu" 
          className="pl-10"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          onKeyDown={(e) => e.key === 'Enter' && handleSearch()}
        />
      </div>

      <Select onValueChange={(value) => onFilterChange('warehouse', value)}>
        <SelectTrigger className="w-[180px]">
          <SelectValue placeholder="Chọn kho" />
        </SelectTrigger>
        <SelectContent>
          <SelectItem value="all">Tất cả kho</SelectItem>
          <SelectItem value="hcm">Kho HCM</SelectItem>
          <SelectItem value="hn">Kho HN</SelectItem>
          <SelectItem value="dn">Kho DN</SelectItem>
        </SelectContent>
      </Select>

      <Select onValueChange={(value) => onFilterChange('status', value)}>
        <SelectTrigger className="w-[180px]">
          <SelectValue placeholder="Trạng thái" />
        </SelectTrigger>
        <SelectContent>
          <SelectItem value="all">Tất cả trạng thái</SelectItem>
          <SelectItem value="confirmed">Đã xác nhận</SelectItem>
          <SelectItem value="packed">Đã đóng gói</SelectItem>
          <SelectItem value="transfering">Đang vận chuyển</SelectItem>
          <SelectItem value="completed">Hoàn thành</SelectItem>
        </SelectContent>
      </Select>

      <Button onClick={handleSearch}>
        <Search className="h-4 w-4 mr-2" />
        Tìm kiếm
      </Button>
    </div>
  );
}
