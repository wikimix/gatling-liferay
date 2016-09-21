package io.gatling.liferay.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import io.gatling.liferay.model.Record;
import io.gatling.liferay.service.RecordLocalServiceUtil;

/**
 * The extended model base implementation for the Record service. Represents a row in the &quot;StressTool_Record&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RecordImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecordImpl
 * @see io.gatling.liferay.model.Record
 * @generated
 */
public abstract class RecordBaseImpl extends RecordModelImpl implements Record {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a record model instance should use the {@link Record} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RecordLocalServiceUtil.addRecord(this);
        } else {
            RecordLocalServiceUtil.updateRecord(this);
        }
    }
}