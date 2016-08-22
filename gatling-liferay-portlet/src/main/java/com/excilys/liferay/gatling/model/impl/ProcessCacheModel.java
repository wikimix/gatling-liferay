package com.excilys.liferay.gatling.model.impl;

import com.excilys.liferay.gatling.model.Process;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Process in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Process
 * @generated
 */
public class ProcessCacheModel implements CacheModel<Process>, Externalizable {
    public long process_id;
    public String name;
    public String type;
    public int order;
    public int pause;
    public long scenario_id;
    public Long recordId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{process_id=");
        sb.append(process_id);
        sb.append(", name=");
        sb.append(name);
        sb.append(", type=");
        sb.append(type);
        sb.append(", order=");
        sb.append(order);
        sb.append(", pause=");
        sb.append(pause);
        sb.append(", scenario_id=");
        sb.append(scenario_id);
        sb.append(", recordId=");
        sb.append(recordId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Process toEntityModel() {
        ProcessImpl processImpl = new ProcessImpl();

        processImpl.setProcess_id(process_id);

        if (name == null) {
            processImpl.setName(StringPool.BLANK);
        } else {
            processImpl.setName(name);
        }

        if (type == null) {
            processImpl.setType(StringPool.BLANK);
        } else {
            processImpl.setType(type);
        }

        processImpl.setOrder(order);
        processImpl.setPause(pause);
        processImpl.setScenario_id(scenario_id);
        processImpl.setRecordId(recordId);

        processImpl.resetOriginalValues();

        return processImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        process_id = objectInput.readLong();
        name = objectInput.readUTF();
        type = objectInput.readUTF();
        order = objectInput.readInt();
        pause = objectInput.readInt();
        scenario_id = objectInput.readLong();
        recordId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(process_id);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        if (type == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(type);
        }

        objectOutput.writeInt(order);
        objectOutput.writeInt(pause);
        objectOutput.writeLong(scenario_id);
        objectOutput.writeLong(recordId);
    }
}
