import { useEffect, useState } from "react";
import { api, type CpuDetail, type SocketOption } from "../api/api";

interface Props {
  cpuId: number;
  onBack: () => void;
}

export default function CpuEdit({ cpuId, onBack }: Props) {
  const [cpu, setCpu] = useState<CpuDetail | null>(null);
  const [sockets, setSockets] = useState<SocketOption[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    Promise.all([api.getCpu(cpuId), api.getSockets()])
      .then(([cpuData, socketData]) => {
        setCpu(cpuData);
        setSockets(socketData);
      })
      .catch((e: Error) => setError(e.message));
  }, [cpuId]);

  if (error) return <p style={{ color: "red" }}>Fehler: {error}</p>;
  if (!cpu) return <p>Lade...</p>;

  function handleChange(field: keyof CpuDetail, value: string | number | null) {
    setCpu((prev) => prev && { ...prev, [field]: value });
  }

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    if (!cpu) return;
    setError(null);
    setSuccess(false);
    try {
      const updated = await api.updateCpu(cpu.id, {
        brand: cpu.brand,
        model: cpu.model,
        socketId: cpu.socketId,
        clockSpeedGhz: cpu.clockSpeedGhz,
        cores: cpu.cores,
        threads: cpu.threads,
        tdpWatt: cpu.tdpWatt,
        priceEur: cpu.priceEur,
        version: cpu.version,
      });
      setCpu(updated);
      setSuccess(true);
    } catch (e: unknown) {
      setError(e instanceof Error ? e.message : "Unbekannter Fehler");
    }
  }

  return (
    <div>
      <button onClick={onBack}>← Zurück</button>
      <h2>CPU bearbeiten</h2>
      {success && <p style={{ color: "green" }}>Gespeichert!</p>}
      {error && <p style={{ color: "red" }}>Fehler: {error}</p>}
      <form onSubmit={handleSubmit}>
        <table cellPadding={6}>
          <tbody>
            <tr>
              <td>Marke</td>
              <td><input value={cpu.brand} onChange={(e) => handleChange("brand", e.target.value)} /></td>
            </tr>
            <tr>
              <td>Modell</td>
              <td><input value={cpu.model} onChange={(e) => handleChange("model", e.target.value)} /></td>
            </tr>
            <tr>
              <td>Sockel</td>
              <td>
                <select
                  value={cpu.socketId}
                  onChange={(e) => handleChange("socketId", Number(e.target.value))}
                >
                  {sockets.map((s) => (
                    <option key={s.id} value={s.id}>{s.name}</option>
                  ))}
                </select>
              </td>
            </tr>
            <tr>
              <td>Taktrate (GHz)</td>
              <td><input type="number" step="0.01" value={cpu.clockSpeedGhz} onChange={(e) => handleChange("clockSpeedGhz", Number(e.target.value))} /></td>
            </tr>
            <tr>
              <td>Kerne</td>
              <td><input type="number" value={cpu.cores} onChange={(e) => handleChange("cores", Number(e.target.value))} /></td>
            </tr>
            <tr>
              <td>Threads</td>
              <td><input type="number" value={cpu.threads} onChange={(e) => handleChange("threads", Number(e.target.value))} /></td>
            </tr>
            <tr>
              <td>TDP (Watt)</td>
              <td><input type="number" value={cpu.tdpWatt ?? ""} onChange={(e) => handleChange("tdpWatt", e.target.value ? Number(e.target.value) : null)} /></td>
            </tr>
            <tr>
              <td>Preis (EUR)</td>
              <td><input type="number" step="0.01" value={cpu.priceEur} onChange={(e) => handleChange("priceEur", Number(e.target.value))} /></td>
            </tr>
          </tbody>
        </table>
        <br />
        <button type="submit">Speichern</button>
      </form>
    </div>
  );
}
