import { useEffect, useState } from "react";
import { api, type CpuSummary } from "../api/api";

interface Props {
  onSelect: (id: number) => void;
}

export default function CpuList({ onSelect }: Props) {
  const [cpus, setCpus] = useState<CpuSummary[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    api.getCpus()
      .then(setCpus)
      .catch((e: Error) => setError(e.message));
  }, []);

  if (error) return <p style={{ color: "red" }}>Fehler: {error}</p>;

  return (
    <div>
      <h2>CPUs</h2>
      <table border={1} cellPadding={8} cellSpacing={0}>
        <thead>
          <tr>
            <th>Marke</th>
            <th>Modell</th>
            <th>Sockel</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {cpus.map((cpu) => (
            <tr key={cpu.id}>
              <td>{cpu.brand}</td>
              <td>{cpu.model}</td>
              <td>{cpu.socketName}</td>
              <td>
                <button onClick={() => onSelect(cpu.id)}>Bearbeiten</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
