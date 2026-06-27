import { useState } from "react";
import CpuList from "./components/CpuList";
import CpuEdit from "./components/CpuEdit";

export default function App() {
  const [selectedId, setSelectedId] = useState<number | null>(null);

  return (
    <div style={{ padding: 24, fontFamily: "sans-serif" }}>
      <h1>CPU Manager</h1>
      {selectedId === null ? (
        <CpuList onSelect={setSelectedId} />
      ) : (
        <CpuEdit cpuId={selectedId} onBack={() => setSelectedId(null)} />
      )}
    </div>
  );
}
